package com.uhi.network_registry_api.repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.uhi.network_registry_api.exceptions.NRAuthException;
import com.uhi.network_registry_api.models.Subscriber;

@Repository
public class SubscriberRepository implements ISubscriberRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(SubscriberRepository.class);

	private static String SQL_CREATE_TEXT = "INSERT INTO public.subscribers(\n"
			+ "	subscriber_id, country, city, domain, unique_key_id, pub_key_id, signing_public_key, encr_public_key, valid_from, valid_to, status, created, updated, radius, type, url)\n"
			+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static String SQL_SEARCH_TEXT = "SELECT participant_id, subscriber_id, country, city, domain, unique_key_id, pub_key_id, signing_public_key, encr_public_key, valid_from, valid_to, status, created, updated, radius, type, url\n"
			+ "	FROM public.subscribers WHERE subscriber_id = ?;";

	private static String SQL_LOOKUP_TEXT_BPP = "SELECT subscriber_id, country, city, domain, unique_key_id, pub_key_id, signing_public_key, encr_public_key, valid_from, valid_to, status, created, updated, radius, type, url\n"
			+ "	FROM public.subscribers WHERE status = 'SUBSCRIBED' and type = 'BPP';";

	private static String SQL_LOOKUP_TEXT_BAP = "SELECT subscriber_id, country, city, domain, unique_key_id, pub_key_id, signing_public_key, encr_public_key, valid_from, valid_to, status, created, updated, radius, type, url\n"
			+ "	FROM public.subscribers WHERE status = 'SUBSCRIBED' and type = 'BAP' and country = ? and city = ? and domain = ? and subscriber_id = ?;";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public Subscriber findSubscriber(String subscriber_id) {
		try {

			@SuppressWarnings("deprecation")
			Subscriber subscriberDetails = jdbcTemplate.queryForObject(SQL_SEARCH_TEXT, new Object[] { subscriber_id },
					subscriberRowMapper);

		} catch (Exception ex) {

		}

		return null;
	}

	@Override
	public List<Subscriber> lookup(Subscriber criteria) {

		List<Subscriber> result = null;

		try {
			if (criteria.getSubscriber_id() == null) {
				result = jdbcTemplate.query(connection -> {
					PreparedStatement ps = connection.prepareStatement(SQL_LOOKUP_TEXT_BPP);
					/*
					 * ps.setString(1, criteria.getCountry()); ps.setString(2, criteria.getCity());
					 * ps.setString(3, criteria.getDomain());
					 */
					return ps;

				}, subscriberRowMapper);
			} else {
				result = jdbcTemplate.query(connection -> {
					PreparedStatement ps = connection.prepareStatement(SQL_LOOKUP_TEXT_BAP);
					/*
					 * ps.setString(1, criteria.getCountry()); ps.setString(2, criteria.getCity());
					 * ps.setString(3, criteria.getDomain()); ps.setString(4,
					 * criteria.getSubscriber_id());
					 */
					return ps;

				}, subscriberRowMapper);
			}
		} catch (Exception ex) {
			throw ex;

		}
		return result;

	}

	@Override
	public Subscriber findSubscriberByParticipantid(Integer participant_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addSubscriber(String subscriber_id, String country, String city, String domain, String unique_key_id,
			String pub_key_id, String signing_public_key, String encr_public_key,
			String status, String radius, String sub_type, String url) {
		// TODO Auto-generated method stub

		KeyHolder keyholder = new GeneratedKeyHolder();

		try {

			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(SQL_CREATE_TEXT,
						java.sql.Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, subscriber_id);
				ps.setString(2, country);
				ps.setString(3, city);
				ps.setString(4, domain);
				ps.setString(5, unique_key_id);
				ps.setString(6, pub_key_id);
				ps.setString(7, signing_public_key);
				ps.setString(8, encr_public_key);
				ps.setString(9, LocalDate.now().toString());
				ps.setString(10, LocalDate.now().plusYears(1).toString());
				ps.setString(11, status);
				ps.setString(12, LocalDate.now().toString());
				ps.setString(13, LocalDate.now().toString());
				ps.setString(14, radius);
				ps.setString(15, sub_type);
				ps.setString(16, url);

				return ps;

			}, keyholder);

		} catch (Exception ex) {

			throw new NRAuthException("Error adding subscriber" + ex.getMessage());
		}

		return null;
	}

	@Override
	public Subscriber updateSubscriber(Integer participant_id, String city, String encr_public_key, String pub_key_id,
			String signing_public_key, String subscriber_id, String unique_key_id, String url) throws NRAuthException {
		// TODO Auto-generated method stub
		return null;
	}

	private org.springframework.jdbc.core.RowMapper<Subscriber> subscriberRowMapper = ((rs, rowNum) -> {

		return new Subscriber(rs.getString("subscriber_id"), rs.getString("country"), rs.getString("city"),
				rs.getString("domain"), rs.getString("unique_key_id"), rs.getString("pub_key_id"),
				rs.getString("signing_public_key"), rs.getString("encr_public_key"), rs.getString("valid_from"),
				rs.getString("valid_to"), rs.getString("status"), rs.getString("created"), rs.getString("updated"),
				rs.getString("radius"), rs.getString("type"), rs.getString("url"));

	});

}
