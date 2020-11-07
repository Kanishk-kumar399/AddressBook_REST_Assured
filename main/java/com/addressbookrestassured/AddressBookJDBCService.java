package com.addressbookrestassured;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBookJDBCService 
{
	List<AddressBookData> addressBookDataList=null;
	private static AddressBookJDBCService addressBookJDBCService;
	public static AddressBookJDBCService getInstance() {
		if(addressBookJDBCService==null) {
			addressBookJDBCService=new AddressBookJDBCService();
		}
		return addressBookJDBCService;
	}
	public Connection getConnection() throws AddressBookJDBCException{
		String jdbcURL = "jdbc:mysql://localhost:3306/addressbook_service?useSSL=false";
		String user = "root";
		String password = "Kanishk111*";
		Connection connection;
		System.out.println("Connecting to database: " + jdbcURL);
		try{
			connection = DriverManager.getConnection(jdbcURL, user, password);
			System.out.println("Connection is SuccessFull!!! " + connection);
			return connection;
		}
		catch(SQLException e)
		{
			throw new AddressBookJDBCException("Unable to establish the connection");
		}
	}

	public List<AddressBookData> readData() throws AddressBookJDBCException{
		String sql = "select * from addressbook";
		List<AddressBookData> addressBookList = new ArrayList<>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String first_name=resultSet.getString("first_name");
				String last_name=resultSet.getString("last_name");
				String address=resultSet.getString("address");
				String city=resultSet.getString("city");
				String state=resultSet.getString("state");
				int zip=resultSet.getInt("zip");
				long phone_number=resultSet.getLong("phone_number");
				String email_id=resultSet.getString("email_id");
				String addressbook_name=resultSet.getString("addressbook_name");
				String addressbook_type=resultSet.getString("addressbook_type");
				addressBookList.add(new AddressBookData(first_name, last_name, address, city, state, zip, phone_number, email_id, addressbook_name, addressbook_type));
			}
		} catch (SQLException e) {
			throw new AddressBookJDBCException("Unable to get data.Please check table");
		}
		return addressBookList;
	}
	public int updateAddressBookDataUsingStatement(String state,int zip,String name) throws AddressBookJDBCException 
	{
		String sql = String.format("update addressbook set state='%s',zip ='%s' where first_name ='%s'",state,zip,name);
		try(Connection con = this.getConnection()) {
			Statement statement=con.createStatement();
			int result=statement.executeUpdate(sql);
			return result;
		}catch (SQLException e) {
			throw new AddressBookJDBCException("Error in updation");
		}
	}
	public List<AddressBookData> getAddressBookDataFromDB(String name) throws AddressBookJDBCException{
		String sql = String.format("select * from addressbook where first_name='%s'",name);
		List<AddressBookData> addressBookList = new ArrayList<>();
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				String first_name=resultSet.getString("first_name");
				String last_name=resultSet.getString("last_name");
				String address=resultSet.getString("address");
				String city=resultSet.getString("city");
				String state=resultSet.getString("state");
				int zip=resultSet.getInt("zip");
				long phone_number=resultSet.getLong("phone_number");
				String email_id=resultSet.getString("email_id");
				String addressbook_name=resultSet.getString("addressbook_name");
				String addressbook_type=resultSet.getString("addressbook_type");
				addressBookList.add(new AddressBookData(first_name, last_name, address, city, state, zip, phone_number, email_id, addressbook_name, addressbook_type));
			}
		} catch (SQLException e) {
			throw new AddressBookJDBCException("Unable to get data.Please check table for updation");
		}
		return addressBookList;
	}
	private List<AddressBookData> getAddressBookFromResultset(ResultSet resultSet) throws AddressBookJDBCException {
		List<AddressBookData> addressBookList = new ArrayList<AddressBookData>();
		try {
			while (resultSet.next()) {
				String first_name=resultSet.getString("first_name");
				String last_name=resultSet.getString("last_name");
				String address=resultSet.getString("address");
				String city=resultSet.getString("city");
				String state=resultSet.getString("state");
				int zip=resultSet.getInt("zip");
				long phone_number=resultSet.getLong("phone_number");
				String email_id=resultSet.getString("email_id");
				String addressbook_name=resultSet.getString("addressbook_name");
				String addressbook_type=resultSet.getString("addressbook_type");
				addressBookList.add(new AddressBookData(first_name, last_name, address, city, state, zip, phone_number, email_id, addressbook_name, addressbook_type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addressBookList;
	}
		
	public List<AddressBookData> getAdressBookDataByStartingDate(LocalDate startDate, LocalDate endDate)
			throws AddressBookJDBCException {
		String sql = String.format("select * from addressbook where date_added between cast('%s' as date) and cast('%s' as date)",
				startDate.toString(), endDate.toString());
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			return this.getAddressBookFromResultset(resultSet);
		} catch (SQLException e) {
			throw new AddressBookJDBCException("Connection Failed.");
		}
	}
	public List<AddressBookData> getContactsByCityOrState(String city, String state) throws AddressBookJDBCException{
		String sql=String.format("Select * from addressbook where city='%s' and state='%s'",city,state);
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			return this.getAddressBookFromResultset(resultSet);
		} catch (SQLException e) {
			throw new AddressBookJDBCException("Connection Failed.");
		}
	}
	public void addContactEntryToDB(String firstName, String lastName, String address, String city, String state,
			int zip, long phoneNumber, String emailId, String addressBookName,String addressBookType,Date dateAdded) throws AddressBookJDBCException{
		Connection connection = null;
		connection = this.getConnection();
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e1) {
			throw new AddressBookJDBCException("Unable to AutoCommit");
		}
		try(Statement statement = connection.createStatement())
		{
			String sql = String.format("insert into addressbook_details " +
					" values ('%s', '%s', '%s', '%s', '%s', '%s', %s, %s, '%s')", emailId, firstName, lastName, address, city, state, zip,  phoneNumber, dateAdded);
			statement.executeUpdate(sql);
			} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new AddressBookJDBCException("Unable to Roll back");
			}
			throw new AddressBookJDBCException("Unable to get data.Please check table for updation");
		}
		try(Statement statement = connection.createStatement()){
			String sql = String.format("insert into address_details_andbookname " + 
					"values ('%s', '%s')", emailId, addressBookName);
			statement.executeUpdate(sql);
		} catch(SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new AddressBookJDBCException("Unable to Roll Back in addressbook_name");
			}
			throw new AddressBookJDBCException("Unable to get data.Please check table for updation in address_name");
		}
		try {
			connection.commit();
		} catch (SQLException e) {
			throw new AddressBookJDBCException("Unable to commit");
		} finally {
			if(connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					throw new AddressBookJDBCException("Unable to get data.Please check table for updation");
				}
		}
		addressBookDataList.add(new AddressBookData(firstName, lastName, address, city, state, zip, phoneNumber, emailId, addressBookName, addressBookType,dateAdded));
	}
	
	public void addContactToDBWithThreads(List<AddressBookData> asList) {
		Map<Integer,Boolean> addressAdditionStatus=new HashMap<Integer,Boolean>();
		addressBookDataList.forEach(addressbookdata->
		{
			Runnable task=()->{
				addressAdditionStatus.put(addressbookdata.hashCode(),false);
				System.out.println("Contact Being Added:"+Thread.currentThread().getName());
				try {
					this.addContactEntryToDB(addressbookdata.getFirst_name(),addressbookdata.getLast_name(),addressbookdata.getAddress(),
							addressbookdata.getCity(),addressbookdata.getState(),addressbookdata.getZip(),addressbookdata.getPhone_number(),
							addressbookdata.getEmail_id(),addressbookdata.getAddressbook_name(),addressbookdata.getAddressbook_type(),addressbookdata.getDate());
				} 
				catch (AddressBookJDBCException e) {
					e.printStackTrace();
				}
				addressAdditionStatus.put(addressbookdata.hashCode(),true);
				System.out.println("Contact Added:"+Thread.currentThread().getName());
			};
			Thread thread=new Thread(task,addressbookdata.getFirst_name());
			thread.start();
		});
		while(addressAdditionStatus.containsValue(false))
		{
			try {
				Thread.sleep(10);
			}
			catch(InterruptedException e) {}
		}
		System.out.println(this.addressBookDataList);
	}
	public int countEntries() {
		return addressBookDataList.size();
	}
}