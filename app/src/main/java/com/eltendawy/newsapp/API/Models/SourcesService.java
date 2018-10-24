
package com.eltendawy.newsapp.API.Models;

import java.util.List;

import com.google.gson.annotations.Expose;
public class SourcesService {

    @Expose
    private List<Source> sources;
    @Expose
    private String status;

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
