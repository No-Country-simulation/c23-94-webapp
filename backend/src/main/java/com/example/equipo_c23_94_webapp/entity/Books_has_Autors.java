package com.example.equipo_c23_94_webapp.entity;

public class Books_has_Autors {

    private Long id;
    private Long booksId;
    private Long autorsId;

    public Books_has_Autors(Long id, Long booksId, Long autorsId) {
        this.id = id;
        this.booksId = booksId;
        this.autorsId = autorsId;
    }

    public Long getId() {
        return id;
    }

    public Long getBooksId() {
        return booksId;
    }

    public Long getAutorsId() {
        return autorsId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBooksId(Long booksId) {
        this.booksId = booksId;
    }

    public void setAutorsId(Long autorsId) {
        this.autorsId = autorsId;
    }
    
}
