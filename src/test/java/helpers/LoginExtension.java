package helpers;

import api.account.AccountApi;
import models.account.LoginRequest;
import models.account.LoginResponse;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static data.Data.USER_NAME;
import static data.Data.USER_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.create;

public class LoginExtension implements BeforeEachCallback, ParameterResolver {

    public static final ExtensionContext.Namespace NAMESPACE = create(LoginExtension.class);
    private final AccountApi accountApi = new AccountApi();

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
        var store = extensionContext.getStore(NAMESPACE);
        var loginRequest = new LoginRequest(USER_NAME, USER_PASSWORD);
        var loginResponse = accountApi.login(loginRequest);
        store.put(extensionContext.getRequiredTestMethod().hashCode(), loginResponse);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getRequiredTestMethod().isAnnotationPresent(WithLogin.class) &&
                parameterContext.getParameter().getType().isAssignableFrom(LoginResponse.class);
    }

    @Override
    public LoginResponse resolveParameter(ParameterContext parameterContext,
                                          ExtensionContext extensionContext) throws ParameterResolutionException {
        var store = extensionContext.getStore(NAMESPACE);
        var loginResponse = store.get(extensionContext.getRequiredTestMethod().hashCode(),
                LoginResponse.class);

        assertThat(loginResponse).isNotNull();
        return loginResponse;
    }
}
