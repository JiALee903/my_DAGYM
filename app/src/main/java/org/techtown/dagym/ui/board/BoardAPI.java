package org.techtown.dagym.ui.board;

import org.techtown.dagym.entity.Board;
import org.techtown.dagym.entity.dto.BoardListResponseDto;
import org.techtown.dagym.entity.dto.BoardSaveDto;
import org.techtown.dagym.entity.dto.BoardSearchDto;
import org.techtown.dagym.entity.dto.CommentDto;
import org.techtown.dagym.entity.dto.FindIdDto;
import org.techtown.dagym.entity.dto.LikeDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BoardAPI {
    // 삭제
    @DELETE("board/delete/{board_id}")
    Call<Board> deleteBoard(@Path("board_id") Long board_id);

    @DELETE("board/comment/delete/{comment_id}")
    Call<Long> deleteComment(@Path("comment_id") Long comment_id);

    // 조회
    @POST("board/select")
    Call<ArrayList<BoardListResponseDto>> selectBoard();

    @POST("board/idSelect")
    Call<FindIdDto> idSelect(@Body LikeDto likeDto);

    @POST("board/like/select")
    Call<ArrayList<FindIdDto>> selectLike(@Body Long member_id);

    @POST("board/comment/select/{board_id}")
    Call<ArrayList<CommentDto>> selectComment(@Path("board_id") Long board_id);

    @POST("board/search")
    Call<ArrayList<BoardListResponseDto>> searchBoard(@Body BoardSearchDto boardSearchDto);
    
    // 등록
    @POST("board/insert/{member_id}")
    Call<Board> insertBoard(@Path("member_id") Long member_id, @Body BoardSaveDto boardSaveDto);

    @POST("board/like/add")
    Call<LikeDto> addLike(@Body LikeDto likeDto);

    @POST("board/comment/insert/{user_id}/{board_id}")
    Call<CommentDto> insertComment(@Path("user_id") String user_id, @Path("board_id") Long board_id, @Body String content);

    // 수정
    @PUT("board/update/{board_id}")
    Call<Board> updateBoard(@Path("board_id") Long board_id, @Body BoardSaveDto boardSaveDto);
}
