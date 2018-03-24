package com.forcelate.services;

import com.forcelate.fetchers.*;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import static graphql.GraphQL.newGraphQL;
import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Service
public class GraphQLService {

    // GraphQL
    private final Resource graphqlsResource;
    // Fetchers
    private final BookDataFetcher bookDataFetcher;
    private final BooksNestedDataFetcher booksNestedDataFetcher;
    private final UserDataFetcher userDataFetcher;
    private final UserNestedDataFetcher userNestedDataFetcher;
    private final UsersDataFetcher usersDataFetcher;

    @Autowired
    public GraphQLService(@Value("classpath:domains.graphqls") Resource graphqlsResource,
                          BookDataFetcher bookDataFetcher,
                          BooksNestedDataFetcher booksNestedDataFetcher,
                          UserDataFetcher userDataFetcher,
                          UserNestedDataFetcher userNestedDataFetcher,
                          UsersDataFetcher usersDataFetcher) {
        this.graphqlsResource = graphqlsResource;
        this.bookDataFetcher = bookDataFetcher;
        this.booksNestedDataFetcher = booksNestedDataFetcher;
        this.userDataFetcher = userDataFetcher;
        this.userNestedDataFetcher = userNestedDataFetcher;
        this.usersDataFetcher = usersDataFetcher;
    }

    public GraphQL getGraphQL() throws IOException {
        File schemaFile = graphqlsResource.getFile();
        TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
        RuntimeWiring runtimeWiring = getRuntimeWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, runtimeWiring);
        return newGraphQL(schema).build();
    }

    private RuntimeWiring getRuntimeWiring() {
        return newRuntimeWiring()
                .type("Query", wiring -> wiring
                        .dataFetcher("user", userDataFetcher)
                        .dataFetcher("users", usersDataFetcher)
                        .dataFetcher("book", bookDataFetcher)
                )
                .type("User", wiring -> wiring
                        .dataFetcher("books", booksNestedDataFetcher)
                )
                .type("Book", wiring -> wiring
                        .dataFetcher("user", userNestedDataFetcher)
                )
                .build();
    }
}
