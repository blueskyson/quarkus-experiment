package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.acme.entity.Release;
import org.acme.entity.ReleaseItem;
import org.acme.repo.ReleaseItemRepository;
import org.acme.repo.ReleaseRepository;

@Path("/release-items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReleaseItemController {

    @Inject
    ReleaseItemRepository releaseItemRepository;

    @Inject
    ReleaseRepository releaseRepository;

    @POST
    @Transactional
    public ReleaseItem createReleaseItem(ReleaseItemInput input) {
        Optional<Release> releaseOpt = releaseRepository.findById(input.releaseId());
        if (releaseOpt.isEmpty()) {
            throw new NotFoundException("Release not found: " + input.releaseId());
        }

        ReleaseItem item = new ReleaseItem();
        item.setId(UUID.randomUUID());
        item.setTaskNum(input.taskNum());
        item.setDescription(input.description());
        item.setNote(input.note());
        item.setDocumentLink(input.documentLink());

        return releaseItemRepository.save(item);
    }

    @GET
    public List<ReleaseItem> getAllReleaseItems() {
        return (List<ReleaseItem>) releaseItemRepository.findAll();
    }

    public record ReleaseItemInput(
        UUID releaseId,
        String taskNum,
        String description,
        String note,
        String documentLink
    ) {}
}
