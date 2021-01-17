package rushb.webapp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "Draft related")
@RequestMapping("api/draft")
@RestController
public class DraftController {
    @ApiOperation("得到对应 creator 的所有 draft")
    @GetMapping("/{creatorId}")
    public ResponseEntity listDrafts(@PathVariable("creatorId") String creatorId){
        return ResponseEntity.ok().body("得到对应 creator 的所有 draft");
    }

    @ApiOperation("得到对应 creator 的指定 draft")
    @GetMapping("/{creatorId}/{draftId}")
    public ResponseEntity getDraft(@PathVariable("creatorId") String creatorId,@PathVariable("draftId") String draftId){
        return ResponseEntity.ok().body("得到对应 creator 的指定 draft");
    }

    @ApiOperation("使用指定用户发布新草稿")
    @PostMapping("/{creatorId}")
    public ResponseEntity postDraft(@RequestBody Map<String, Object> jsonObject){
        return ResponseEntity.ok().body("使用指定用户发布新文章");
    }

    @ApiOperation("使用指定用户更新对应文章")
    @PutMapping("/{creatorId}/{articleId}")
    public ResponseEntity putDraft(@PathVariable("creatorId") String questionId,@PathVariable("articleId") String articleId){
        return ResponseEntity.ok().body("使用指定用户更新对应文章");
    }

    @ApiOperation("删除对应用户的对应文章（将文章状态改为\"回收站\"）")
    @DeleteMapping("/{creatorId}/{articleId}")
    public ResponseEntity deleteDraft(@PathVariable("creatorId") String questionId,@PathVariable("articleId") String articleId){
        return ResponseEntity.ok().body("删除对应用户的对应文章（将文章状态改为\"回收站\"）");
    }
}
