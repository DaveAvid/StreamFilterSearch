package com.david.ui;

public interface Person {
    /**
     * Returns the first name of an Actor or Director of type Person
     *
     * @return firstName First name of an Actor or Director that was a part of a Movie or Television Show
     */
    public String getFirstName();

    /**
     * Sets the first name of an Actor or Director of type Person
     *
     * @param firstName first name of an Actor or Director
     */
    public void setFirstName(String firstName);

    /**
     * Returns the last name of an Actor or Director of type Person
     *
     * @return lastName Last name of an Actor or Director who was involved in making a Television Show or Movie
     */
    public String getLastName();

    /**
     * Sets the last name of an Actor or Director of type Person
     *
     * @param lastName last name of an Actor or Director
     */
    public void setLastName(String lastName);

    /**
     * Returns the firstName and lastName of an Actor or Director of type Person
     *
     * @return fullName FullName is firstName + " " + lastName
     */
    public String getFullName();
}
