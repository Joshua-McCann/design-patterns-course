package creation.prototype;

public class CopyConstructorDemo {

    private static class Address {
        public String streetAddress, city, country;

        public Address(String streetAddress, String city, String country) {
            this.streetAddress = streetAddress;
            this.city = city;
            this.country = country;
        }

        // this is considered better than implementing cloneable
        public Address(Address other) {
            this.streetAddress = other.streetAddress;
            this.city = other.city;
            this.country = other.country;
        }

        @Override
        public String toString() {
            return "Address{" +
                    "streetAddress='" + streetAddress + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }

        public static class Employee {
            public String name;
            public Address address;

            public Employee(String name, Address address) {
                this.name = name;
                this.address = address;
            }

            public Employee(Employee other) {
                this.name = other.name;
                this.address = new Address(other.address);
            }

            @Override
            public String toString() {
                return "Employee{" +
                        "name='" + name + '\'' +
                        ", address=" + address +
                        '}';
            }
        }
    }
}
