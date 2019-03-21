package test.org.cloudCorp.dao.Impl;

import static org.junit.jupiter.api.Assertions.*;

import org.cloudCorp.dao.UserInfoDao;
import org.cloudCorp.dao.Impl.UserInfoDaoImpl;
import org.cloudCorp.model.UserInfo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserInfoDaoImplTest {
	private static UserInfoDao userInfoDao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		userInfoDao =new UserInfoDaoImpl();
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		userInfoDao =null;
		
		
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testRegister() {
		//fail("Not yet implemented");
		UserInfo userInfo=new UserInfo();
		userInfo.setMobilePhone("13855669988");
		userInfo.setPassword("password");
		assertFalse(userInfoDao.register(userInfo));
		
	}

}
