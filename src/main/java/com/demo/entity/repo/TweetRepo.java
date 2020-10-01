package com.demo.entity.repo;

import com.demo.entity.TweetEntity;
import com.demo.entity.TweetView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepo extends PagingAndSortingRepository<TweetEntity, Long> {
    Page<TweetView> findAllProjectedByOrderByDateTimeDesc(Pageable pageable);
    List<TweetEntity> findTop20ByOrderByDateTimeDesc();
}
