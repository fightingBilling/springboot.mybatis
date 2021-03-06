package org.ceeker.web.sbootm.common.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class DbCountRunner implements CommandLineRunner {
    protected final Logger logger = LoggerFactory.getLogger(DbCountRunner.class);

    @Autowired
    private Collection<CrudRepository> repositories;

    public DbCountRunner(Collection<CrudRepository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public void run(String... strings) throws Exception {
        for (CrudRepository crudRepository : repositories) {
            logger.info(String.format("%s has %s entries", getRepositoryName(crudRepository.getClass()), crudRepository.count()));
        }
    }

    protected static String getRepositoryName(Class crudRepositoryClass) {
        for (Class repositoryInterface : crudRepositoryClass.getInterfaces()) {
            if (repositoryInterface.getName().startsWith("org.ceeker.web.sbootm.domain")) {
                return repositoryInterface.getSimpleName();
            }
        }
        return "UnknownRepository";
    }
}