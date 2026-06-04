package org.application.crm_DDD.features.identity_context.application.use_case.login_account;

import com.github.f4b6a3.uuid.UuidCreator;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextDomainException;
import org.application.crm_DDD.features.identity_context.domain.exception.IdentityContextExceptionReasonPhrase;
import org.application.crm_DDD.features.identity_context.domain.model.account.Account;
import org.application.crm_DDD.features.identity_context.domain.model.account.AccountId;
import org.application.crm_DDD.features.identity_context.domain.model.account.Password;
import org.application.crm_DDD.features.identity_context.domain.model.account.Username;
import org.application.crm_DDD.features.identity_context.domain.model.session.Session;
import org.application.crm_DDD.features.identity_context.domain.model.session.SessionId;

public class LoginAccountService implements LoginAccountUseCase {
    private final LoginAccountRepositoryPort loginAccountRepositoryPort;
    private final LoginAccountPasswordEncoderPort loginAccountPasswordEncoderPort;
    private final LoginAccountSessionRepositoryPort loginAccountRefreshTokenRepositoryPort;
    private final LoginAccountTokenGeneratorPort loginAccountTokenGeneratorPort;


    @Override
    public LoginAccountResult execute(
            String _username,
            String _rawPassword
    ) {
        Username username = new Username(_username);
        Password rawPassword = new Password(_rawPassword);

        Account account = this.loginAccountRepositoryPort.findByUsername(username)
                .orElseThrow(() -> new IdentityContextDomainException(null, IdentityContextExceptionReasonPhrase.ACCOUNT_NOT_FOUND));

        account.authenticate(rawPassword, this.loginAccountPasswordEncoderPort);

        SessionId sessionId = new SessionId(UuidCreator.getTimeOrderedEpoch());
        AccountId accountId = account.getId();

        Session session = Session.createNew(sessionId, accountId);

        this.loginAccountRefreshTokenRepositoryPort.save(session);

        String accessToken = this.loginAccountTokenGeneratorPort.generateAccessToken(account.toSnapshot());

        return new LoginAccountResult(session.getId().value(), accessToken, Session.EXPIRATION_MINUTES);
    }

    public LoginAccountService(LoginAccountRepositoryPort loginAccountRepositoryPort, LoginAccountPasswordEncoderPort loginAccountPasswordEncoderPort, LoginAccountSessionRepositoryPort loginAccountRefreshTokenRepositoryPort, LoginAccountTokenGeneratorPort loginAccountTokenGeneratorPort) {
        this.loginAccountRepositoryPort = loginAccountRepositoryPort;
        this.loginAccountPasswordEncoderPort = loginAccountPasswordEncoderPort;
        this.loginAccountRefreshTokenRepositoryPort = loginAccountRefreshTokenRepositoryPort;
        this.loginAccountTokenGeneratorPort = loginAccountTokenGeneratorPort;
    }
}
