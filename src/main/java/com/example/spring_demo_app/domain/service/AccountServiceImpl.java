package com.example.spring_demo_app.domain.service;

import com.example.spring_demo_app.common.contants.ShopeeConstants;
import com.example.spring_demo_app.data.model.AccountModel;
import com.example.spring_demo_app.data.services.AccountService;
import com.example.spring_demo_app.domain.entity.AccountEntity;
import com.example.spring_demo_app.repository.AccountRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.spring_demo_app.common.HeaderStored.MEDIA_TYPE;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final OkHttpClient client;
    private final ModelMapper mapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, OkHttpClient client, ModelMapper mapper) {
        this.accountRepository = accountRepository;
        this.client = client;
        this.mapper = mapper;
    }

    @Override
    public void saveAccount(AccountModel accountModel) {
        AccountEntity entity = mapper.map(accountModel, AccountEntity.class);
        boolean b = accountRepository.exists(Example.of(new AccountEntity(entity.getUserid())));
        if (b) return;
        accountRepository.save(entity);
    }

    @Override
    public void updateAccount(AccountModel accountModel) {
        AccountEntity entity = mapper.map(accountModel, AccountEntity.class);
    accountRepository.delete(new AccountEntity(entity.getUserid()));

        accountRepository.save(entity);
    }

    @Override
    public Response login(String json) throws IOException {
        RequestBody body = RequestBody.create(json, MEDIA_TYPE);

        Request request = new Request.Builder().url(ShopeeConstants.ShopeeUrl.BASE_URL + ShopeeConstants.ShopeeAuth.LOGIN)
                .addHeader("x-csrftoken", "aWKV70orJMVv1Rl7T2xoDUx7X0Rhxvii")
                .addHeader("cookie", "SPC_F=gY2N4BOQiNOVGnnu9oFW6NpJFgfoeS6k; csrftoken=aWKV70orJMVv1Rl7T2xoDUx7X0Rhxvii")
                .post(body)
                .build();

        return client.newCall(request).execute();
    }
}
