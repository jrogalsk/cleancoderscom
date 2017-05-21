package com.jrsoft.learning.cleancoderscom.fixtures;

import com.jrsoft.learning.cleancoderscom.Codecast;
import com.jrsoft.learning.cleancoderscom.Context;
import com.jrsoft.learning.cleancoderscom.GateKeeper;
import com.jrsoft.learning.cleancoderscom.MockGateway;
import com.jrsoft.learning.cleancoderscom.PresentCodecastUseCase;
import com.jrsoft.learning.cleancoderscom.PresentableCodecast;
import com.jrsoft.learning.cleancoderscom.User;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

public class CodecastPresentation {
    private PresentCodecastUseCase useCase = new PresentCodecastUseCase();
    private GateKeeper gateKeeper = new GateKeeper();

    public CodecastPresentation() {
        Context.gateway = new MockGateway();
    }

    public boolean addUser(String username) {
        Context.gateway.save(new User(username));
        return true;
    }

    public boolean loginUser(String username) {
        User user = Context.gateway.findUser(username);
        if (nonNull(user)) {
            gateKeeper.setLoggedInUser(user);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean createLicenceForViewing(String user, String codecast) {
        return false;
    }

    public String presentationUser() {
        return gateKeeper.getLoggedInUser().getUserName();
    }

    public boolean clearCodecasts() {
        new ArrayList<Codecast>(Context.gateway
                .findAllCodecasts())
                .forEach(codecast -> Context.gateway.delete(codecast));

        return Context.gateway.findAllCodecasts().size() == 0;
    }

    public int countOfCodecastsPresented() {
        List<PresentableCodecast> presentations = useCase.presentCodecasts(gateKeeper.getLoggedInUser());
        return presentations.size();
    }

}
