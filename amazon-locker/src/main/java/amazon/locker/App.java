package amazon.locker;

import amazon.locker.entities.AmazonLocker;
import amazon.locker.entities.Compartment;
import amazon.locker.enums.CompartmentSize;
import amazon.locker.strategy.ExpirationStrategy;
import amazon.locker.strategy.SizeBasedExpirationStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Compartment small = new Compartment("1", CompartmentSize.SMALL);
        Compartment medium = new Compartment("2", CompartmentSize.MEDIUM);
        Compartment large = new Compartment("3", CompartmentSize.LARGE);

        List<Compartment> compartments = new ArrayList<>();
        compartments.add(small);
        compartments.add(medium);
        compartments.add(large);

        ExpirationStrategy expirationStrategy = new SizeBasedExpirationStrategy();

        AmazonLocker amazonLocker = new AmazonLocker(compartments, expirationStrategy);

        String codeSmall1 = amazonLocker.generateCodeAndPutPackage(CompartmentSize.SMALL);
        System.out.println("Your parcel is kept in locker succesfully you can access with code: " + codeSmall1);

        String codeSmall2 = amazonLocker.generateCodeAndPutPackage(CompartmentSize.SMALL);

    }
}
