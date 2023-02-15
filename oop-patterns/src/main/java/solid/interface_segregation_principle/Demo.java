package solid.interface_segregation_principle;

class Document {

}

interface Machine {
    void print(Document d);
    void fax(Document d);
    void scan(Document d);
}

class MultiFunctionPrinter implements Machine {
    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

class OldFashionedPrinter implements Machine {
    @Override
    public void print(Document d) {

    }

    // because the old-fashioned printer has to use the same interface, the code gets wonky here.
    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface Printer {
    void print(Document d);
}

interface Faxer {
    void fax(Document d);
}

interface Scanner {
    void scan(Document d);
}

class JustAPrinter implements Printer {
    @Override
    public void print(Document d) {

    }
}

class PhotoCopier implements Printer, Scanner {
    @Override
    public void print(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

interface MultiFunctionDevice extends Printer, Scanner, Faxer { }

class MultiFunctionMachine implements MultiFunctionDevice {
    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}

public class Demo {
}
