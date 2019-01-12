package com.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.DAO.IitemDAO;
import com.config.SpringWebConfig;
import com.exceptions.UserException;
import com.model.Item;
import com.model.User;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringWebConfig.class)
public class ItemTests {

	@Autowired
	private IitemDAO itemDAO;

	@Test
	public void testGetItem() throws SQLException {

		String description = "ИНТЕРНЕТ - SMART TV ОТВОРЕН БРАУЗЪР ВГРАДЕНА Wi-Fi МРЕЖОВА КАРТА ДИСПЛЕЙ: 49 (124 см.) 16:9 РАЗДЕЛИТЕЛНА СПОСОБНОСТ: ULTRA HD 3840X2160 ВХОДОВЕ: 2хHDMI, USB, COMPOSITE, YPBPR, VGA изход за слушалки дигитален тунер: DVB-T/C ДИСТАНЦИОННО УПРАВЛЕНИЕ";

		Item actualItem = itemDAO.getItem(3);
		Item testItem = new Item("NEO LED-49418 UHD SW", 4, 599.0f, 10, 1, description, "img/Neo 49418.jpg");

		assertEquals(testItem.getName(), actualItem.getName());
	}

	@Test
	public void testGetBrandId() {

		long testid = 1;
		long actualId = itemDAO.getIDbyBrandName("Sony");
		assertEquals(testid, actualId);
	}

	@Test
	public void testGetCategoryId() {

		long testid = 1;
		long actualId = itemDAO.getIDbyCategoryName("TV");
		assertEquals(testid, actualId);
	}

}
