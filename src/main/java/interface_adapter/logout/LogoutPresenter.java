package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;
    private final LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           LoggedInViewModel loggedInViewModel,
                           LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {
        // --- 1. Update the LoggedInState ---
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(""); // clear the logged-in username
        loggedInViewModel.firePropertyChange();

        // --- 2. Update the LoginState ---
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername()); // optionally prefill username
        loginState.setPassword(""); // clear password
        loginViewModel.firePropertyChange();

        // --- 3. Switch to the Login View ---
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
