package com.addressbookrestassured;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AddressBoookREST_IOServiceTest 
{
	@Before
	public void setup()
	{
		RestAssured.baseURI="http://localhost";
		RestAssured.port=3000;
	}
	public AddressBookData[] getAddressBookContactList()
	{
		Response response=RestAssured.get("/ContactDetails");
		System.out.println("AddressBook Contact Entries In JSONServer:\n"+response.asString());
		AddressBookData[] arrayOfContacts=new Gson().fromJson(response.asString(),AddressBookData[].class);
		return arrayOfContacts;
	}
    @Test
    public void givenAddressBookDataInJSONServer_WhenRetrieved_ShouldMatchTheCount()
    {
        AddressBookData[] arrayOfContacts=getAddressBookContactList();
        AddressBookREST_IOService addressBookREST_IOService;
        addressBookREST_IOService=new AddressBookREST_IOService(Arrays.asList(arrayOfContacts));
        long entries=addressBookREST_IOService.countREST_IOEntries();
        Assert.assertEquals(2,entries);
    }
    @Test
    public void givenNewEmployeeWhenAddedShouldMatch201ResponseAndcount()
    {
    	AddressBookService addressBookService;
    	AddressBookData[] arrayOfContacts=getAddressBookContactList();
    	addressBookService=new AddressBookService(Arrays.asList(arrayOfContacts));
    	AddressBookData addressBookData=new AddressBookData("Kanishk","Kumar","Sh-333","Agra","UP",2122,55281195,"kasdnsd@gmail.com");
    	Response response=addContactToJsonServer(addressBookData);
    	int HTTPstatusCode=response.getStatusCode();
    	Assert.assertEquals(201,HTTPstatusCode);
    	addressBookData=new Gson().fromJson(response.asString(),AddressBookData.class);
    	addressBookService.addContactToAddressBookUsingRestAPI(addressBookData);
    	long entries=addressBookService.countREST_IOEntries();
    	Assert.assertEquals(3,entries);
    }
    public Response addContactToJsonServer(AddressBookData addressBookData) {
		String contactsJson=new Gson().toJson(addressBookData);
		RequestSpecification request=RestAssured.given();
		request.header("Content-Type","application/json");
		request.body(contactsJson);
		return request.post("/ContactDetails");
	}
}
