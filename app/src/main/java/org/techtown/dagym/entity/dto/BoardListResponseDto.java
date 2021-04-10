package org.techtown.dagym.entity.dto;

import android.os.Parcel;
import android.os.Parcelable;

import org.techtown.dagym.entity.Member;

import java.time.LocalDateTime;

public class BoardListResponseDto{
    private Long id;
    private String title;
    private String user_id;
    private String content;
    private int hit;
    private int recommends;
    private int likes;
    private LocalDateTime modifiedDate;

    public BoardListResponseDto(Long id, String title, String user_id, String content, int hit, int recommends, int likes, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.user_id = user_id;
        this.content = content;
        this.hit = hit;
        this.recommends = recommends;
        this.likes = likes;
        this.modifiedDate = modifiedDate;
    }

    protected BoardListResponseDto(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        user_id = in.readString();
        content = in.readString();
        hit = in.readInt();
        recommends = in.readInt();
        likes = in.readInt();
    }

//    public static final Creator<BoardListResponseDto> CREATOR = new Creator<BoardListResponseDto>() {
//        @Override
//        public BoardListResponseDto createFromParcel(Parcel in) {
//            return new BoardListResponseDto(in);
//        }
//
//        @Override
//        public BoardListResponseDto[] newArray(int size) {
//            return new BoardListResponseDto[size];
//        }
//    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public int getRecommends() {
        return recommends;
    }

    public void setRecommends(int recommends) {
        this.recommends = recommends;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//
//        if (id == null) {
//            dest.writeByte((byte) 0);
//        } else {
//            dest.writeByte((byte) 1);
//            dest.writeLong(id);
//        }
//        dest.writeString(title);
//        dest.writeString(user_id);
//        dest.writeString(content);
//        dest.writeInt(hit);
//        dest.writeInt(recommends);
//        dest.writeInt(likes);
//    }
//}
