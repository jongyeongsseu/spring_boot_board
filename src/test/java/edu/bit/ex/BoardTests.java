package edu.bit.ex;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.bit.ex.mapper.BoardMapper;
import edu.bit.ex.vo.BoardVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendWith(SpringExtension.class) // 5.x
@SpringBootTest
class BoardTests {

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private DataSource dataSource;

	@Test
	public void testDataSource() {
		System.out.println("DataSource=" + dataSource);

		try (Connection conn = dataSource.getConnection()) {
			System.out.println("Cooooooooonn=" + conn);

			assertThat(conn).isInstanceOf(Connection.class);
			assertEquals(100, getLong(conn, "select 100 from dual"));
			// assertTrue(0 < getLong(conn, "select count(*) from User"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private long getLong(Connection conn, String sql) {
		long result = 0;
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = rs.getLong(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@Test
	public void testGetList() {

		System.out.println(boardMapper);
		System.out.println(boardMapper.getList().size());

		for (BoardVO vo : boardMapper.getList()) {
			log.info(vo.getbName());
			System.out.println(vo.getbName());
		}
	}
}
