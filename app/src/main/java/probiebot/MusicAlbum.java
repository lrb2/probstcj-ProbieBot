/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package probiebot;

/**
 *
 * @author hellokitty
 */

public class MusicAlbum {
    private String genre, album, artist, releaseDate, img, link;
    public MusicAlbum(String genre, String album, String artist, String releaseDate) throws IllegalArgumentException{
        if(genre!=null && album!=null && artist!=null && releaseDate !=null){
            this.genre = genre;
            this.artist = artist;
            this.album = album;
            this.releaseDate = releaseDate;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    public String getGenre(){
        return genre;
    }
    public String getAlbum(){
        return album;
    }
    public String getArtist(){
        return artist;
    }
    public String getReleaseDate(){
        return releaseDate;
    }
    public String getImg(){
        return img;
    }
    public String getLink(){
        return link;
    }
    public void setImg(String img) throws IllegalArgumentException{
        if(img != null){
            this.img = img;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    public void setLink(String link) throws IllegalArgumentException{
        if(link != null){
            this.link = link;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String toString(){
        return artist + "-" + album + "(" + releaseDate + ")";
    }
}
