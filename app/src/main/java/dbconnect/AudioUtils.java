package dbconnect;

/**
 * Created by Baldwin on 19/12/5.
 */

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;

/**
 *
 * @ClassName: com.example.mediastore.AudioUtils
 * @Description: 音频文件帮助类
 * @author zhaokaiqiang
 * @date 2014-12-4 上午11:39:45
 *
 */
public class AudioUtils{

    /**
     * 获取sd卡所有的音乐文件
     *
     * @return
     * @throws Exception
     */
    public static ArrayList<SongInfo> getAllSongs(Context context) {

        ArrayList<SongInfo> songs = null;

        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,        //歌曲文件名
                        MediaStore.Audio.Media.TITLE,               //歌曲原名
                        MediaStore.Audio.Media.DURATION,            //歌曲时长
                        MediaStore.Audio.Media.ARTIST,              //歌曲演唱者
                        MediaStore.Audio.Media.ALBUM,               //专辑名
                        MediaStore.Audio.Media.DATA},               //歌曲文件路径
                MediaStore.Audio.Media.MIME_TYPE + "=? or "
                        + MediaStore.Audio.Media.MIME_TYPE + "=?",
                new String[] { "audio/mpeg", "audio/x-ms-wma" }, null);

        songs = new ArrayList<SongInfo>();

        if (cursor.moveToFirst()) {

            SongInfo song = null;

            do {
                song = new SongInfo();

                // 文件名
                song.setFileName(cursor.getString(1));
                // 歌曲名
                song.setTitle(cursor.getString(2));
                // 时长
                song.setDuration(cursor.getInt(3));
                // 歌手名
                song.setArtist(cursor.getString(4));
                // 专辑名
                song.setAlbum(cursor.getString(5));
                // 文件路径
                if (cursor.getString(6) != null) {
                    song.setUrl(cursor.getString(6));
                }

                songs.add(song);
                song.save();
            } while (cursor.moveToNext());

            cursor.close();

        }
        return songs;
    }

}