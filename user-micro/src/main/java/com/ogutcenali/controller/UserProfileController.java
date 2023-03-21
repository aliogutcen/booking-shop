package com.ogutcenali.controller;

import com.ogutcenali.dto.request.UpdateUserProfileRequestDto;
import com.ogutcenali.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import static com.ogutcenali.constant.RestEndPoints.*;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;
    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> updateUserProfile(@RequestBody UpdateUserProfileRequestDto updateUserProfileRequestDto){
        userProfileService.updateUserProfile(updateUserProfileRequestDto);
        return ResponseEntity.ok(
                userProfileService.updateUserProfile(updateUserProfileRequestDto));
    }
    @DeleteMapping(DELETE+"/{id}")
    public ResponseEntity<Boolean> deleteUserProfile(@PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.deleteUserProfile(id));
    }


}
