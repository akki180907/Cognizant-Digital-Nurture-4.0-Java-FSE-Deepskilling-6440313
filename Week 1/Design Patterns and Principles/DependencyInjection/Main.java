public class Main {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl(); // Low-level module
        CustomerService service = new CustomerService(repository);    // High-level module

        service.getCustomerDetails("CSE001");
    }
}
