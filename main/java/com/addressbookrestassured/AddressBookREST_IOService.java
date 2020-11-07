package com.addressbookrestassured;

import java.util.ArrayList;
import java.util.List;

public class AddressBookREST_IOService 
{
	List<AddressBookData> addressBookDataList;
    public AddressBookREST_IOService(List<AddressBookData> contactList) {
		addressBookDataList=new ArrayList<>(contactList);
	}
	public long countREST_IOEntries() {
		return addressBookDataList.size();
	}
}
