package com.test.userinterface.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.test.userinterface.domain.Component;
import com.test.userinterface.domain.ComponentPage;
import com.test.userinterface.domain.ComponentProperty;
import com.test.userinterface.domain.ComponentType;
import com.test.userinterface.domain.Event;
import com.test.userinterface.domain.Rule;

public class UserInterfaceDao {
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String url="jdbc:oracle:thin:@172.25.14.10:1521:g10u0";
	private String driverClass ="oracle.jdbc.driver.OracleDriver";
	private String userName ="ARGO_US_R3_GS_DEV";
	private String password ="ARGO_US_R3_GS_DEVpwd";

	public List<Component> getComponents() throws Exception {
		try {

			Class.forName(driverClass);

			connect = DriverManager.getConnection(url, userName, password);

			// preparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("SELECT CPNT_ID, CPNT_NAME, CPNT_TYPE FROM T_CPNT");

			resultSet = preparedStatement.executeQuery();

			ArrayList<Component> componentList = new ArrayList<Component>();


			while (resultSet.next()) {
				Component component = new Component();
				Long componentId = resultSet.getLong("CPNT_ID");
				String componentName = resultSet.getString("CPNT_NAME");
				int componentType = resultSet.getInt("CPNT_TYPE");

				component.setComponentId(componentId);
				component.setComponentName(componentName);
				component.setComponentType(ComponentType.getType(componentType));
				componentList.add(component);
			}
			return componentList;
		}
		catch (Exception e) {
			throw e;
		} finally {
			preparedStatement.close();
			resultSet.close();
			connect.close();
		}

	} 
	public Component getComponent(long componentId) throws Exception{


		try {
			Class.forName(driverClass);

			connect = DriverManager.getConnection(url, userName, password);

			// preparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("SELECT T_CPNT.CPNT_ID , CPNT_IMGE,CPNT_FILE,"
							+ "CPNT_CNT,T_PRTY.PRTY_ID,PRTY_NAME,PRTY_DESC FROM T_CPNT "
							+ "INNER JOIN T_CPNT_PRTY_ASSC ON T_CPNT.CPNT_ID = T_CPNT_PRTY_ASSC.CPNT_ID "
							+ "INNER JOIN T_PRTY ON T_CPNT_PRTY_ASSC.PRTY_ID = T_PRTY.PRTY_ID "
							+ "WHERE T_CPNT.CPNT_ID=?");
			preparedStatement.setLong(1, componentId);

			resultSet = preparedStatement.executeQuery();
			Component component = new Component();
			Map<Long,ComponentProperty> propertyMap = new HashMap<Long, ComponentProperty>();
			while (resultSet.next()) {
				component.setComponentId(resultSet.getLong("CPNT_ID"));
				component.setConfigurationContents(new Image(Display.getDefault(),resultSet.getBlob("CPNT_IMGE").getBinaryStream()));
				component.setConfigurationFile(resultSet.getString("CPNT_CNT"));
				component.setComponentFileName(resultSet.getString("CPNT_FILE"));
				if(!propertyMap.containsKey(resultSet.getLong("PRTY_ID"))){
					ComponentProperty property = new ComponentProperty();
					property.setPropertyId(resultSet.getLong("PRTY_ID"));
					property.setPropertyDesc(resultSet.getString("PRTY_DESC"));
					property.setPropertyName(resultSet.getString("PRTY_NAME"));
					propertyMap.put(property.getPropertyId(), property);
				}
			}
			component.setPropertyList((List<ComponentProperty>) propertyMap.values());
			return component;
		}
		catch (Exception e) {
			throw e;
		} finally {
			preparedStatement.close();
			resultSet.close();
			connect.close();
		}


	}

	//get the page list
	public List<ComponentPage> getPages() throws Exception {
		try {
			Class.forName(driverClass);

			connect = DriverManager.getConnection(url, userName, password);

			// preparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("SELECT PAGE_ID, PAGE_NAME FROM T_PAGE");

			resultSet = preparedStatement.executeQuery();

			List<ComponentPage> pageList = new ArrayList<ComponentPage>();


			while (resultSet.next()) {
				ComponentPage page = new ComponentPage();
				Long pageId = resultSet.getLong("PAGE_ID");
				String pageName = resultSet.getString("PAGE_NAME");

				page.setPageId(pageId);
				page.setPageName(pageName);
				pageList.add(page);

			}
			return pageList;
		}
		catch (Exception e) {
			throw e;
		} finally {
			preparedStatement.close();
			resultSet.close();
			connect.close();
		}

	}



	public ComponentPage getPage(long pageId) throws Exception{


		try {
			Class.forName(driverClass);

			connect = DriverManager.getConnection(url, userName, password);

			// preparedStatements can use variables and are more efficient
			preparedStatement = connect
					.prepareStatement("SELECT T_PAGE.PAGE_ID, PAGE_NAME, "
							+ "T_CPNT.CPNT_ID, CPNT_NAME,CPNT_IMGE "
							+ "T_EVNT.EVNT_ID, EVNT_NAME, "
							+ "T_RULE.RULE_ID,RULE_NAME "
							+ "FROM T_PAGE INNER JOIN T_PAGE.PAGE_ID = T_PAGE_CMPT_EVNT_RULE_ASSC.PAGE_ID "
							+ "INNER JOIN T_EVNT ON T_EVNT.EVNT_ID = T_PAGE_CMPT_EVNT_RULE_ASSC.EVNT_ID "
							+ "INNER JOIN T_RULE ON T_RULE.RULE_ID = T_PAGE_CMPT_EVNT_RULE_ASSC.RULE_ID "
							+ "WHERE T_PAGE.PAGE_ID =?");
			preparedStatement.setLong(1, pageId);

			resultSet = preparedStatement.executeQuery();
			ComponentPage page = new ComponentPage();
			Map<Long,Component> componentMap = new HashMap<Long, Component>();
			Map<Long,Event> eventMap = new HashMap<Long, Event>();
			Map<Long,Rule> ruleMap = new HashMap<Long, Rule>();
			while (resultSet.next()) {
				page.setPageId(resultSet.getLong("PAGE_ID"));
				page.setPageName(resultSet.getString("PAGE_NAME"));
				if(!componentMap.containsKey(resultSet.getLong("CPNT_ID"))){
					Component component = new Component();
					component.setComponentId(resultSet.getLong("CPNT_ID"));
					component.setComponentName(resultSet.getString("CPNT_NAME"));
					component.setConfigurationContents(new Image(Display.getDefault(),resultSet.getBlob("CPNT_IMGE").getBinaryStream()));
					componentMap.put(component.getComponentId(), component);
				}
				if(!eventMap.containsKey(resultSet.getLong("EVNT_ID"))){
					Event event = new Event();
					event.setEventId(resultSet.getLong("EVNT_ID"));
					event.setEvnetName(resultSet.getString("EVNT_NAME"));
					eventMap.put(event.getEventId(), event);
				}
				if(!ruleMap.containsKey(resultSet.getLong("RULE_ID"))){
					Rule rule = new Rule();
					rule.setRuleId(resultSet.getLong("RULE_ID"));
					rule.setRuleName(resultSet.getString("RULE_NAME"));
					ruleMap.put(rule.getRuleId(), rule);
				}
			}


			page.setComponents((List<Component>) componentMap.values());
			page.setEvents((List<Event>) eventMap.values());
			page.setRules((List<Rule>) ruleMap.values());
			return page;
		}
		catch (Exception e) {
			throw e;
		} finally {
			preparedStatement.close();
			resultSet.close();
			connect.close();
		}
	}
}
