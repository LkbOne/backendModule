package com.example.hugh.life.dao.api;

import com.example.hugh.life.dao.entity.BookInfoEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookInfoDao {
    boolean add(BookInfoEntity entity);

    BookInfoEntity queryByDouBanId(@Param("doubanId") String doubanId);
    List<BookInfoEntity> recommendByPagesInfo(@Param("userId") String userId, @Param("min") Integer min,
                                              @Param("max") Integer max, @Param("topN") Integer topN);
    List<BookInfoEntity> getBookInfoExceptUserId(@Param("userId") String userId, @Param("topN") Integer topN);

    List<BookInfoEntity> listByLikeTitle(@Param("content") String content, @Param("topN") Integer topN);

    List<BookInfoEntity> getOtherPeopleUser(@Param("userId") String userId);

    List<BookInfoEntity> getTheCommonLikePeople(@Param("userId") String userId);

    List<String> getBookInfoId(@Param("userId") String userId);

    String getTagsIdByUserId(@Param("userId")  String userId);

    List<BookInfoEntity> getBookInfoByTags(@Param("tagsId") String tagsId);
}
