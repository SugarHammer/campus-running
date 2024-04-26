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
        label-width="100px"
        :rules="dataRule"
      >
        <el-form-item label="服务名称" prop="seatName">
          <el-input
            v-model="dataForm.seatName"
            placeholder="服务名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="所属服务分类" prop="roomId">
          <el-select
            v-model="dataForm.roomId"
            placeholder="所属服务分类"
            clearable
          >
            <el-option
              v-for="item in optionsRoom"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务状态" prop="roomState">
          <el-select
            v-model="dataForm.roomState"
            placeholder="服务状态"
            clearable
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
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
        seatName: "",
        roomState: "",
        roomId: ''
      },
      pageIndex: 1,
      pageSize: 10,
      options: [
        {
          value: "0",
          label: "开放"
        },
        {
          value: "1",
          label: "已预约"
        }
      ],
      dataRule: {
        seatName: [
          { required: true, message: "服务名称不能为空", trigger: "blur" }
        ],
        roomState: [
          { required: true, message: "服务状态不能为空", trigger: "blur" }
        ],
        roomId: [
          { required: true, message: "所属服务分类不能为空", trigger: "blur" }
        ]
      },
      optionsRoom: []
    };
  },

  mounted() {},

  methods: {
    init(row) {

      this.$http({
        url: this.$http.adornUrl("/basstudyroom/list"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.optionsRoom = data.data.content.map((item) =>{
            return {
              value: item.roomId,
              label: item.roomName
            }
          })
        }
      });

      console.log(row);
      this.userInfo = row || 0;
      this.visible = true;
      if (row) {
        this.dataForm = JSON.parse(JSON.stringify(row));
        this.dataForm.roomState = JSON.parse(JSON.stringify( String( row.roomState)))
      }
    },
    // 提交
    dataFormSubmit() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          let func = "";
          if (this.userInfo) {
            func = "/basseat/update";
          } else {
            func = "/basseat/save";
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
