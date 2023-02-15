package creation.builder;

public class FacetedBuilderDemo {

    public static void main(String[] args) {
        Person person = new PersonBuilder()
                .lives()
                .at("732 Cloverdale Pl")
                .in("Aurora")
                .withPostCode("53090")
                .works()
                .at("Dev10")
                .as("Instructor")
                .forAnnualPay(120000)
                .build();
        System.out.println(person);
    }

    // builder facade
    static class PersonBuilder {
        protected Person person = new Person();

        public PersonAddressBuilder lives() {
            return new PersonAddressBuilder(person);
        }

        public PersonEmploymentBuilder works() {
            return new PersonEmploymentBuilder(person);
        }

        public Person build() {
            return person;
        }
    }

    static class PersonAddressBuilder extends PersonBuilder {

        public PersonAddressBuilder(Person person) {
            this.person = person;
        }

        public PersonAddressBuilder at(String streetAddress) {
            person.streetAddress = streetAddress;
            return this;
        }

        public PersonAddressBuilder withPostCode(String postCode) {
            person.postcode = postCode;
            return this;
        }

        public PersonAddressBuilder in(String city) {
            person.city = city;
            return this;
        }
    }

    static class PersonEmploymentBuilder extends PersonBuilder {
        public PersonEmploymentBuilder(Person person) {
            this.person = person;
        }

        public PersonEmploymentBuilder at(String companyName) {
            person.companyName = companyName;
            return this;
        }

        public PersonEmploymentBuilder as(String position) {
            person.position = position;
            return this;
        }

        public PersonEmploymentBuilder forAnnualPay(int annualIncome) {
            person.annualIncome = annualIncome;
            return this;
        }
    }

    static class Person {
        // address
        public String streetAddress, postcode, city;

        //employment
        public String companyName, position;
        public int annualIncome;

        @Override
        public String toString() {
            return "Person{" +
                    "streetAddress='" + streetAddress + '\'' +
                    ", postcode='" + postcode + '\'' +
                    ", city='" + city + '\'' +
                    ", companyName='" + companyName + '\'' +
                    ", position='" + position + '\'' +
                    ", annualIncome=" + annualIncome +
                    '}';
        }
    }
}
