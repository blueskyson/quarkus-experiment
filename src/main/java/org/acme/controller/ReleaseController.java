package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.acme.entity.Release;
import org.acme.repo.ReleaseRepository;

@Path("/releases")
public class ReleaseController {

    @Inject
    ReleaseRepository releaseRepository;

    @POST
    @Transactional
    public Release createRelease(Release release) {
        if (release.getId() == null) {
            release.setId(UUID.randomUUID());
        }
        return releaseRepository.save(release);
    }

    @GET
    public List<Release> getAllReleases() {
        return (List<Release>) releaseRepository.findAll();
    }

    @GET
    @Path("/join-items")
    public List<Map<String, Object>> getJoinedReleases() {
        List<Object[]> raw = releaseRepository.findReleaseJoinItems();
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : raw) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("releaseId", row[0]);
            map.put("releaseTaskNum", row[1]);
            map.put("version", row[2]);
            map.put("itemId", row[3]);
            map.put("itemTaskNum", row[4]);
            map.put("description", row[5]);
            result.add(map);
        }

        return result;
    }
}
