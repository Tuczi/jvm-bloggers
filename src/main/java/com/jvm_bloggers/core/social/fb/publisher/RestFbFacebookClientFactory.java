package com.jvm_bloggers.core.social.fb.publisher;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.jvm_bloggers.ApplicationProfiles.PRODUCTION;

@Component
@Profile({PRODUCTION})
class RestFbFacebookClientFactory implements FacebookClientFactory {

    private final PageAccessTokenProvider patProvider;
    private final String appSecret;

    @Autowired
    RestFbFacebookClientFactory(Environment env, PageAccessTokenProvider patProvider) {
        this.appSecret = env.getProperty("fb.app.secret");
        this.patProvider = patProvider;
    }

    public FacebookClient createFacebookClient() {
        return new DefaultFacebookClient(patProvider.getToken(), appSecret, Version.LATEST);
    }

}
