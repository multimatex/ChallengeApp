package com.rapidticket.platform.infrastructure.adapters.in.controller;

import com.rapidticket.platform.application.dto.ShowResponseDTO;
import com.rapidticket.platform.application.usecase.GetShowInfoUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/getallinfo")
public class GetShowInfoController {
    private final GetShowInfoUseCase getShowInfoUseCase;

    public GetShowInfoController(GetShowInfoUseCase getShowInfoUseCase) {
        this.getShowInfoUseCase = getShowInfoUseCase;
    }

    @GetMapping
    public Flux<ShowResponseDTO> getAllShows() {
        return getShowInfoUseCase.getAllShowInfo();
    }
}
