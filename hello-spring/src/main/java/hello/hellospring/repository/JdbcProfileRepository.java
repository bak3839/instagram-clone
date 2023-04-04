package hello.hellospring.repository;

import hello.hellospring.domain.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcProfileRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProfileRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Profile save(Profile profile) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("profile").usingGeneratedKeyColumns("profileNum");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("imageName", profile.getImageName());
        parameters.put("message", profile.getMessage());
        parameters.put("link", profile.getLink());
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        profile.setProfileNum(key.longValue());
        return profile;
    }

}
