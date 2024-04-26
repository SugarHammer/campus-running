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
        <el-form-item label="服务分类名称" prop="roomName">
          <el-input
            v-model="dataForm.roomName"
            placeholder="服务分类名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="开放时间">
          <el-time-picker
            is-range
            v-model="valueTime"
            range-separator="-"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
            placeholder="选择时间范围"
            value-format="HH-mm"
            style="width: 310.3px;"
            @change="studyChange"
          >
          </el-time-picker>
        </el-form-item>
        <el-form-item label="所属地点" prop="floorId">
          <el-select
            v-model="dataForm.floorId"
            placeholder="所属地点"
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
      valueTime: "",
      dataForm: {
        roomName: "",
        floorId: "",
        openingTime: "",
        closeTime: ""
      },
      dataRule: {
        roomName: [
          { required: true, message: "服务分类名称不能为空", trigger: "blur" }
        ],
        floorId: [
          { required: true, message: "所属地点不能为空", trigger: "blur" }
        ],
        valueTime: [
          { required: true, message: "开放时间不能为空", trigger: "blur" }
        ]
      },
      options: [],
      pageIndex: 1,
      pageSize: 10
    };
  },

  mounted() {},

  methods: {
    studyChange(info) {
      console.log(info);
    },
    init(row) {
      this.$http({
        url: this.$http.adornUrl("/basfloor/list"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.options = data.data.list.map(item => {
            return {
              value: item.id,
              label: item.floor
            };
          });
        }
      });
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
          if (!this.valueTime) {
            this.$message({
              message: "请选择开放时间",
              type: "warning"
            });
            return false;
          } else {
            this.dataForm.openingTime = this.valueTime[0].replace(/-/g, ":");
            this.dataForm.closeTime = this.valueTime[1].replace(/-/g, ":");
            let func = "";
            if (this.userInfo) {
              func = "/basstudyroom/update";
            } else {
              func = "/basstudyroom/save";
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
