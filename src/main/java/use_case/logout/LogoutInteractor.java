package use_case.logout;

/**
 * The Logout Interactor.
 */
public class LogoutInteractor implements LogoutInputBoundary {
    private final LogoutUserDataAccessInterface userDataAccessObject;
    private final LogoutOutputBoundary logoutPresenter;

    public LogoutInteractor(LogoutUserDataAccessInterface userDataAccessInterface,
                            LogoutOutputBoundary logoutOutputBoundary) {
        // Save the DAO and Presenter in the instance variables.
        this.userDataAccessObject = userDataAccessInterface;
        this.logoutPresenter = logoutOutputBoundary;
    }

    @Override
    public void execute() {
        // 1. Retrieve the current username from the DAO.
        String username = userDataAccessObject.getCurrentUsername();

        // 2. Clear the current user (log out).
        userDataAccessObject.setCurrentUsername(null);

        // 3. Create LogoutOutputData with the username that just logged out.
        LogoutOutputData logoutOutputData = new LogoutOutputData(username);

        // 4. Tell the presenter to prepare the success view.
        logoutPresenter.prepareSuccessView(logoutOutputData);
    }
}

