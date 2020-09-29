package com.demo.entity.repo;

import com.demo.entity.TweetEntity;
import com.demo.entity.TweetView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepo extends PagingAndSortingRepository<TweetEntity, Long> {
    Page<TweetView> findAllProjectedBy(Pageable pageable);
}
