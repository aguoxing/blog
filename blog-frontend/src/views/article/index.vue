<template>
  <div>
    <div class="blog-bg">
      <div class="blog-title">{{ article.title }}</div>
      <div class="blog-item">
        <div class="blog-time">{{ article.createTime }} | create by {{ article.author }}</div>
        <div class="blog-type">
          分类：{{ article.category.categoryName }}
        </div>
      </div>

      <div class="blog-content">
        <no-ssr>
          <mavon-editor
            v-model="article.content"
            :subfield="false"
            :box-shadow="false"
            default-open="preview"
            :toolbars-flag="false"
            :ishljs="true"
          />
        </no-ssr>
      </div>

      <div class="blog-action">
        <div class="blog-meta">
          <div class="blog-icon"><Icon type="ios-eye" size="18" />
            {{ article.views }}
          </div>
          <div class="blog-icon"><Icon type="ios-text" size="18" />
            {{ article.commentCount }}
          </div>
          <div class="blog-icon"><Icon type="md-thumbs-up" size="18" />
            {{ article.likeCount }}
          </div>
        </div>
        <div class="last-update">
          最后更新于：{{ article.updateTime }}
        </div>
      </div>
    </div>

    <div class="blog-comment">
      <Card :dis-hover="true">
        <p slot="title">
          评论
        </p>
        <Dropdown slot="extra">
          <a href="javascript:void(0)">
            <div v-if="name">已登录</div>
            <div v-else>
              登录
              <Icon type="ios-arrow-down" />
            </div>
          </a>
          <DropdownMenu slot="list">
            <DropdownItem>Github</DropdownItem>
            <DropdownItem>QQ</DropdownItem>
          </DropdownMenu>
        </Dropdown>

        <div class="input-comment">
          <div class="user-avatar">
            <Avatar icon="ios-person" :src="avatar" />
          </div>
          <div class="right" style="width:95%">
            <Input v-model="comment.message" type="textarea" :rows="4" placeholder="Enter something..." />
            <div v-if="name" class="send-msg">
              <div class="msg-info">
                还能输入300字
              </div>
              <div class="send-btn">
                <Button>取消</Button>
                <Button type="info" @click="toComment">发送</Button>
              </div>
            </div>
          </div>
        </div>
        <Divider />
        <div v-for="item in message" :key="item.id" class="comment-all">
          <div class="comment-container">
            <div class="left-avatar">
              <Avatar icon="ios-person" :src="item.avatar" />
            </div>
            <div class="right-msg">
              <div class="comment-name">
                {{ item.user }} | {{ item.createTime }}
              </div>
              <div class="comment-msg">
                <p>{{ item.message }}</p>
                <p><a href="">回复</a></p>
              </div>
            </div>
          </div>
          <div v-for="replyItem in item.reply" :key="replyItem.id" class="reply-container">
            <div class="left-avatar">
              <Avatar icon="ios-person" :src="replyItem.avatar" />
            </div>
            <div class="right-msg">
              <div class="comment-name">
                {{ replyItem.user }} | {{ replyItem.createTime }}
              </div>
              <div class="comment-msg">
                <p>{{ replyItem.message }}</p>
                <p><a href="">回复</a></p>
              </div>
            </div>
          </div>
        </div>
      </Card>
    </div>
  </div>

</template>
<script>
import articleApi from '@/api/article'
import commentApi from '@/api/comment'
import { mapGetters } from 'vuex'
export default {
  data() {
    return {
      article: {},
      comment: {},
      message: {}
    }
  },
  computed: {
    ...mapGetters(['name']),
    ...mapGetters(['avatar'])
  },
  created() {
    const articleId = this.$route.params.id
    this.getArticle(articleId)
    this.listComment(articleId)
  },
  methods: {
    getArticle(articleId) {
      articleApi.readById(articleId).then(res => {
        this.article = res.data
      })
    },
    listComment(id) {
      commentApi.listComment(id).then(res => {
        this.message = res.data
        console.log(res.data)
      })
    },
    toComment() {
      const articleId = this.$route.params.id
      this.comment.articleId = articleId
      this.comment.user = this.name
      commentApi.toComment(this.comment).then(res => {
        this.$Message.success('评论成功')
        this.comment.message = ''
        this.listComment(articleId)
      })
    }
  }
}
</script>

<style scoped>
.blog-bg{
    background: #ffffff;
    padding: 30px 20px;
}
.blog-title{
    text-align: center;
    font-size: 24px;
    font-weight: bold;
    line-height: 60px;
}
.blog-item{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-bottom: 30px;
}
.blog-time{
  font-size: 14px;
}
.blog-icon{
  color: gray;
  display: flex;
  margin-right: 6px;
}
.blog-action{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    margin-top: 15px;
}
.blog-meta{
    display: flex;
    color: #3e4149;
    font-size: 14px;
}
.blog-comment{
  margin-top: 15px;
}
.input-comment{
    display: flex;
    justify-content: space-between;
  flex-direction:row;
}
.send-msg{
  display: flex;
  flex-direction:row;
  justify-content: space-between;
  margin-top: 6px;
}
.comment-container{
  display: flex;
  flex-direction: row;
}
.left-avatar{
  margin-right: 15px;
}
.right-msg{
  display: flex;
  flex-direction: column;
}
.comment-name{
  margin-bottom: 10px;
}
.comment-msg{
  margin-bottom: 10px;
}
.reply-container{
  margin-left: 50px;
  display: flex;
  flex-direction: row;
}
</style>
