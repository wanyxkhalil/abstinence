package io.wanyxkhalil.abstinence.wubjpa.sample;

import io.wanyxkhalil.abstinence.common.domain.request.PageReq;
import io.wanyxkhalil.abstinence.wubjpa.sample.domain.DebitOrder;
import io.wanyxkhalil.abstinence.wubjpa.sample.repository.DebitOrderRepository;
import io.wanyxkhalil.abstinence.wubjpa.utils.WebRequestSortJpaUtils;
import io.wanyxkhalil.abstinence.wubjpa.web.response.PageRespJpaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;

@SpringBootApplication
public class SampleApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Autowired
    private DebitOrderRepository debitOrderRepository;

    @Override
    public void run(String... args) throws Exception {
        PageReq pageReq = new PageReq();
        pageReq.setPageNum(1);
        pageReq.setPageSize(3);

        Page<DebitOrder> page = debitOrderRepository.findAll(WebRequestSortJpaUtils.getPageInfoAsc(pageReq, "id"));

        PageRespJpaData data = new PageRespJpaData();
        data.buildPageInfo(page);
        System.out.println(data);

        page.forEach(System.out::println);
    }
}
