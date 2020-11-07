package com.addressbookrestassured;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressBookService
{
	public AddressBookJDBCService addressbookJDBCService;
	private List<AddressBookData> addressBookDataList;
	public AddressBookService(){
		this.addressbookJDBCService = AddressBookJDBCService.getInstance();
	}
	public AddressBookService(List<AddressBookData> contactList) {
		this();
		addressBookDataList=new ArrayList<>(contactList);
	}
	public List<AddressBookData> readAddressBookData() throws AddressBookJDBCException{
		return this.addressbookJDBCService.readData();
	}
	public void updateContactDetails(String state,int zip,String name) throws AddressBookJDBCException
	{
		new AddressBookJDBCService().updateAddressBookDataUsingStatement(state,zip,name);
		this.addressBookDataList=this.addressbookJDBCService.readData();
		AddressBookData addressBookData=this.getAddressBookData(name);
		if(addressBookData!=null) 
			{
			addressBookData.setZip(zip);
			addressBookData.setState(state);
			}
	}
	public AddressBookData getAddressBookData(String name) {
		return this.addressBookDataList.stream()
				.filter(addressBookDataListObject->addressBookDataListObject.getFirst_name().equals(name))
				.findFirst().orElse(null);
	}

	public boolean checkAddressBookInSyncWithDB(String name) throws AddressBookJDBCException {
		List<AddressBookData> addressBookDataList=new AddressBookJDBCService().getAddressBookDataFromDB(name);
		return addressBookDataList.get(0).equals(getAddressBookData(name));
	}
	public List<AddressBookData> getEmployeePayrollDataByStartDate(LocalDate startDate, LocalDate endDate)throws AddressBookJDBCException {
		return this.addressbookJDBCService.getAdressBookDataByStartingDate(startDate, endDate);
	}

	public List<AddressBookData> getContactsByCityOrState(String city, String state) throws AddressBookJDBCException {
		return this.addressbookJDBCService.getContactsByCityOrState(city,state);
	}
	public long countREST_IOEntries() {
		return addressBookDataList.size();
	}
	public void addContactToAddressBookUsingRestAPI(AddressBookData addressBookData) {
		addressBookDataList.add(addressBookData);
	}
}
