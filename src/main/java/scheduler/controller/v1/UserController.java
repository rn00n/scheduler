package scheduler.controller.v1;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import scheduler.advice.exception.CUserNotFoundException;
import scheduler.entity.User;
import scheduler.model.response.CommonResult;
import scheduler.model.response.ListResult;
import scheduler.model.response.SingleResult;
import scheduler.repository.UserJpaRepository;
import scheduler.service.ResponseService;

@Api(tags = {"2. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {

    private final UserJpaRepository userJpaRepository;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value= "회원 리스트 조회", notes = "모든 회원을 조회한다.")
    @GetMapping("/users")
    public ListResult<User> findAllUser() {
        return responseService.getListResult(userJpaRepository.findAll());
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value= "회원 단건 조회", notes = "회원번호(id)로 회원을 조회한다.")
    @GetMapping("/user")
    public SingleResult<User> findUser(@ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        return responseService.getSingleResult(userJpaRepository.findByUid(id).orElseThrow(CUserNotFoundException::new));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping("/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam long id,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name ){
        User user = User.builder()
                .id(id)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepository.save(user));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{id}")
    public CommonResult delete(
            @ApiParam(value = "회원정보", required = true) @PathVariable long id ) {
        userJpaRepository.deleteById(id);
        return responseService.getSuccessResult();
    }


}

