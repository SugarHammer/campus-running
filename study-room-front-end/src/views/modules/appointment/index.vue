<template>
  <div class="mod-role">
    <el-form
      :inline="true"
      :model="dataForm"
      @keyup.enter.native="getDataList()"
    >
      <el-form-item>
        <el-input
          v-model="dataForm.seatName"
          placeholder="预约者名称"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="dataForm.seatPhone"
          placeholder="预约者电话"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="dataForm.seatState"
          placeholder="预约状态"
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
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;"
    >
      <el-table-column
        prop="seatName"
        header-align="center"
        align="center"
        label="用户名称"
      >
      </el-table-column>
      <el-table-column
        prop="seatClass"
        header-align="center"
        align="center"
        label="备注"
      >
      </el-table-column>
      <el-table-column
        prop="seatPhone"
        header-align="center"
        align="center"
        label="联系方式"
        :show-overflow-tooltip="true"
      >
      </el-table-column>
      <el-table-column
        prop="seatState"
        header-align="center"
        align="center"
        label="预约状态"
        :show-overflow-tooltip="true"
      >
        <template slot-scope="scope">
          <div class="table-contents">
            <el-tag
              :type="
                scope.row.seatState == 0
                  ? 'success'
                  : scope.row.seatState == 2
                  ? 'danger'
                  : 'warning'
              "
            >
              {{
                scope.row.seatState == 0
                  ? "审核通过"
                  : scope.row.seatState == 2
                  ? "审核未通过"
                  : "未审核"
              }}
            </el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="seatTime"
        header-align="center"
        align="center"
        width="180"
        label="预约时间"
      >
      </el-table-column>
      <el-table-column
        prop="updateTime"
        header-align="center"
        align="center"
        width="180"
        label="修改时间"
      >
      </el-table-column>
      <el-table-column
        fixed="right"
        header-align="center"
        align="center"
        width="150"
        label="操作"
      >
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="examineHandle(scope.row)">
            {{ scope.row.seatState !== 0 ? "审核通过" : "审核不通过" }}
          </el-button>
          <el-button
            type="text"
            size="small"
            @click="deleteHandle(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "SelfStudyIndex",

  data() {
    return {
      dataForm: {
        seatPhone: "",
        seatName: "",
        seatState: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      options: [
        {
          value: "0",
          label: "通过"
        },
        {
          value: "1",
          label: "未通过"
        }
      ]
    };
  },

  mounted() {},

  activated() {
    this.getDataList();
  },

  methods: {
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/basappointment/list"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          seatPhone: this.dataForm.seatPhone,
          seatName: this.dataForm.seatName,
          seatState: this.dataForm.seatState,
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.content;
          this.totalPage = data.data.totalCount;
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 删除
    deleteHandle(id) {
      this.$confirm(`确定删除此预约?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/basappointment/delete"),
            method: "delete",
            data: [id]
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 500,
                onClose: () => {
                  this.getDataList();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        })
        .catch(() => {});
    },
    // 审核
    examineHandle(info) {
      let func = "";
      let tips = "";
      if (info.seatState) {
        console.log("通过");
        func = "/basappointment/stateOn";
        tips = "确定通过审核?";
      } else {
        console.log('未通过')
        func = "/basappointment/stateOff";
        tips = "确定将此预约不通过审核?";
      }

      this.$confirm(tips, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl(func + "?id=" + info.id + '&' + 'seatId=' + info.seatId),
            method: "post"
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$message({
                message: "操作成功",
                type: "success",
                duration: 500,
                onClose: () => {
                  this.getDataList();
                }
              });
            } else {
              this.$message.error(data.msg);
            }
          });
        })
        .catch(() => {});
    }
  }
};
</script>

<style lang="scss" scoped></style>
