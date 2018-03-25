package ru.zardi.tests.services;

import com.google.code.stackexchange.client.StackExchangeApiClient;
import com.google.code.stackexchange.client.StackExchangeApiClientFactory;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.Question;
import com.google.code.stackexchange.schema.StackExchangeSite;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zardi.tests.web.QueryModel;

/**
 * Created by Anton Petrov
 * Time: 12:34
 * Date: 2018-03-25.
 */
@SuppressWarnings("WeakerAccess")
@Service
public class StackOverflowService {
    public final static String SIMULATE_EXCEPTION_SEARCH = "special-words-to-generate-exception-in-our-code";

    private StackExchangeApiClient stackExchangeApiClient;

    public StackOverflowService() {
        stackExchangeApiClient = StackExchangeApiClientFactory.newInstance(null, StackExchangeSite.STACK_OVERFLOW).createStackExchangeApiClient();
    }

    public PagedList<Question> search(QueryModel queryModel) {
        if (SIMULATE_EXCEPTION_SEARCH.equals(queryModel.getSearch())) {
            throw new RuntimeException("Some exception");
        }
        return (!StringUtils.isEmpty(queryModel.getSearch())) ?
                stackExchangeApiClient
                        .searchQuestions(queryModel.getSearch(), queryModel.getSortOrder(), new Paging(queryModel.getPageNumber(), queryModel.getPageSize())) :
                null;
    }
}
