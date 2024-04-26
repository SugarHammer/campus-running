<template>
  <div>
    <el-dialog
      :title="!userInfo ? '新增' : '修改'"
      :close-on-click-modal="false"
      width="40%"
      :visible.sync="visible"
      @close="cancel"
    >
      <el-form
        :model="dataForm"
        ref="dataForm"
        @keyup.enter.native="dataFormSubmit()"
        label-width="80px"
        :rules="dataRule"
      >
        <el-form-item label="公告标题" prop="noticeTitle">
          <el-input
            v-model="dataForm.noticeTitle"
            placeholder="公告标题"
          ></el-input>
        </el-form-item>
        <el-form-item label="公告内容" prop="noticeContent">
          <el-input
            type="textarea"
            v-model="dataForm.noticeContent"
            :rows="4"
            placeholder="公告内容"
            resize="none"
            :show-word-limit="true"
          ></el-input>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input-number
            v-model="dataForm.sort"
            controls-position="right"
            :min="0"
            style="width: 100px"
          />
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取消</el-button>
        <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "SelfStudyNoticeAddOrUpdate",

  data() {
    return {
      uploadImgUrl: this.$http.adornUrl("/file/upload"),
      userInfo: 0,
      dataForm: {
        noticeTitle: "",
        noticeContent: "",
        sort: ''
      },
      visible: false,
      dataRule: {
        noticeTitle: [
          { required: true, message: "公告标题不能为空", trigger: "blur" }
        ],
        noticeContent: [
          { required: true, message: "公告内容不能为空", trigger: "blur" }
        ]
      }
    };
  },

  mounted() {},

  computed: {
    headers() {
      return {
        token: "bafa4f22667279d4d4e7555acdb41447" // 获取token
      };
    }
  },

  methods: {
    init(row) {
      console.log(row);
      this.userInfo = row || 0;
      this.visible = true;
      if (row) {
        this.dataForm = JSON.parse(JSON.stringify(row));
      }
    },
    // 添加
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          if (this.userInfo) {
            console.log("编辑");
            this.$http({
              url: this.$http.adornUrl("/notice/update"),
              method: "post",
              data: this.$http.adornData({
                noticeTitle: this.dataForm.noticeTitle,
                noticeContent: this.dataForm.noticeContent,
                sort: this.dataForm.sort,
                noticeId: this.dataForm.noticeId
              })
            }).then(res => {
              console.log(res);
              if (res.data && res.data.code === 0) {
                this.$message({
                  message: "操作成功",
                  type: "success",
                  duration: 500,
                  onClose: () => {
                    this.visible = false;
                    this.$emit("refreshDataList");
                  }
                });
              } else {
                this.$message.error(res.data.msg);
              }
            });
          } else {
            this.$http({
              url: this.$http.adornUrl("/notice/save"),
              method: "post",
              data: this.$http.adornData(this.dataForm)
            }).then(res => {
              console.log(res);
              if (res.data && res.data.code === 0) {
                this.$message({
                  message: "操作成功",
                  type: "success",
                  duration: 500,
                  onClose: () => {
                    this.visible = false;
                    this.$emit("refreshDataList");
                  }
                });
              } else {
                this.$message.error(res.data.msg);
              }
            });
          }
        }
      });
    },
    // 上传
    handleAvatarSuccess(res, file) {
      console.log(res.data.url);
      this.dataForm.noticeImg = res.data.url;
    },
    // 取消
    cancel() {
      this.$emit("handCancel");
    }
  }
};
</script>

<style lang="scss" scoped>
</style>
