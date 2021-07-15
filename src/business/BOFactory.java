package business;

import business.custom.impl.CourseBOImpl;
import business.custom.impl.RegistrationBOImpl;
import business.custom.impl.StudentBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType) {
        switch (boType) {
            case STUDENT:
                return (T) new StudentBOImpl();
            case COURSE:
                return (T) new CourseBOImpl();
            case REGISTRATION:
                return (T) new RegistrationBOImpl();
            default:
                return null;
        }
    }
}
