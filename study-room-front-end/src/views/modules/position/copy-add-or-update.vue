<template>
  <div>
    <el-dialog
      :title="!userInfo ? '新增' : '修改'"
      :close-on-click-modal="false"
      width="30%"
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
        <el-form-item label="地点名称" prop="floor">
          <el-input v-model="dataForm.floor" placeholder="地点名称"></el-input>
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
  name: "SelfStudyCopyAddOrUpdate",

  data() {
    return {
      visible: false,
      userInfo: "",
      dataForm: {
        floor: ""
      },
      dataRule: {
        floor: [
          { required: true, message: "地点名称不能为空", trigger: "blur" }
        ]
      }
    };
  },

  mounted() {},

  methods: {
    init(row) {
      console.log(row);
      this.userInfo = row || 0;
      this.visible = true;
      if (row) {
        this.dataForm = JSON.parse(JSON.stringify(row));
      }
    },
    // 提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          let func = "";
          if (this.userInfo) {
            func = "/basfloor/update";
          } else {
            func = "/basfloor/save";
          }
          this.$http({
            url: this.$http.adornUrl(func),
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
      });
    },
    // 取消
    cancel() {
      this.$emit("handCancel");
    }
  }
};
</script>

<style lang="scss" scoped></style>
