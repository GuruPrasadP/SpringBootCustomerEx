package com.springboot.builder;

import org.apache.commons.lang3.builder.Builder;

import com.springboot.domain.Address;

interface IAddressBuilder{
	CustomerBuilder done();
}

public class AddressBuilder implements IAddressBuilder, Builder<Address>{
	
	private String streetLine1 = "default streetLine1";
	private String country = "default country";
	private String zipcode = "default zipcode";
	
	private CustomerBuilder parentBuilder;
	
	public AddressBuilder(CustomerBuilder parentBuilder){
		this.parentBuilder = parentBuilder;
	}

	@Override
	public Address build() {
		Address address = new Address();
		address.setCountry(country);
		address.setStreetLine1(streetLine1);
		address.setZipcode(zipcode);
		return address;
	}
	
	public CustomerBuilder done(){
		return this.parentBuilder;
	}
	
	public AddressBuilder withStreetLine1(String streetLine1) {
		this.streetLine1 = streetLine1;
		return this;
	}
	
	public AddressBuilder withCountry(String country) {
		this.country = country;
		return this;
	}
	
	public AddressBuilder withZipcode(String zipcode) {
		this.zipcode = zipcode;
		return this;
	}

}
