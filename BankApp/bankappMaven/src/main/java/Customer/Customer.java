package Customer;

import java.util.Date;

public class Customer {

    int cust_id;
    String email;
    String password;
    String firstName;
    String lastName;
    String ssn;
    Date dateOfBirth;
    String contactNo;
    String street;
    String city;
    String state;
    int zip;
    String country;
    String mobile_banking;



    // Constructors
    public Customer() {

    }

    public Customer(int cust_id, String email, String password, String firstName, String lastName, String ssn, Date dateOfBirth, String contactNo, String street, String city, String state, int zip, String country, String mobile_banking) {
        this.cust_id = cust_id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.contactNo = contactNo;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.mobile_banking = mobile_banking;
    }

    public Customer(String email, String password, String firstName, String lastName, String ssn, Date dateOfBirth,
                    String contactNo, String street, String city, String state, int zip, String country) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.dateOfBirth = dateOfBirth;
        this.contactNo = contactNo;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }
// getters and setters


    public int getCust_id() {
        return cust_id;
    }

    public void setCust_id(int cust_id) {
        this.cust_id = cust_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMobile_banking() {
        return mobile_banking;
    }

    public void setMobile_banking(String mobile_banking) {
        this.mobile_banking = mobile_banking;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cust_id=" + cust_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", contactNo='" + contactNo + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", country='" + country + '\'' +
                ", mobile_banking='" + mobile_banking + '\'' +
                '}';
    }
}
