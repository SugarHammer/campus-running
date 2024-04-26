<template>
  <div class="mod-role">
    <el-form
      :inline="true"
      :model="dataForm"
      @keyup.enter.native="getDataList()"
    >
      <el-form-item>
        <el-input
          v-model="dataForm.name"
          placeholder="用户名称"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="dataForm.mobile"
          placeholder="预约者电话"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="dataForm.status"
          placeholder="账号状态"
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
        prop="username"
        header-align="center"
        align="center"
        label="用户名称"
      >
      </el-table-column>
      <el-table-column
        prop="mobile"
        header-align="center"
        align="center"
        label="联系方式"
      >
      </el-table-column>
      <el-table-column
        prop="qq"
        header-align="center"
        align="center"
        label="QQ"
      >
      </el-table-column>
      <el-table-column
        prop="email"
        header-align="center"
        align="center"
        label="邮箱"
      >
      </el-table-column>
      <el-table-column header-align="center" align="center" label="用户状态">
        <template slot-scope="scope">
          <div class="table-contents">
            <el-tag :type="scope.row.status ? 'success' : 'danger'">
              {{ scope.row.status ? "正常" : "禁用" }}
            </el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        header-align="center"
        align="center"
        width="180"
        label="创建时间"
      >
      </el-table-column>
      <el-table-column
        prop="bz"
        header-align="center"
        align="center"
        width="180"
        label="备注"
        :show-overflow-tooltip="true"
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
          <el-button
            type="text"
            size="small"
            @click="addOrDisableHandle(scope.row)"
            :class="scope.row.status ? 'btn_color' : ''"
          >
            {{ scope.row.status ? "禁用" : "解禁" }}
          </el-button>
          <el-button
            type="text"
            size="small"
            @click="deleteHandle(scope.row.userId)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="total, sizes, prev, pager, next, jumper"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: "SelfStudyIndex",

  data() {
    return {
      dataForm: {
        name: "",
        mobile: "",
        status: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      options: [
        {
          value: "0",
          label: "禁用"
        },
        {
          value: "1",
          label: "正常"
        },
      ]
    };
  },

  mounted() {},

  activated() {
    this.getDataList();
  },

  methods: {
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
    getDataList() {
      this.dataListLoading = true;
      this.$http({
        url: this.$http.adornUrl("/tbuser/list"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          name: this.dataForm.name,
          mobile: this.dataForm.mobile,
          status: this.dataForm.status
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.content;
          this.totalPage = Number(data.data.totalCount);
        } else {
          this.dataList = [];
          this.totalPage = 0;
        }
        this.dataListLoading = false;
      });
    },
    // 禁用、解禁
    addOrDisableHandle(info) {
      let func = "";
      let tips = "";
      if (info.status) {
        console.log("禁用");
        func = "/tbuser/ban";
        tips = "确定禁用此账号?";
      } else {
        console.log("解禁");
        func = "/tbuser/relieveBan";
        tips = "确定将此账号接触封禁?";
      }

      this.$confirm(tips, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl(func + "?id=" + info.userId),
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
    },
    // 删除
    deleteHandle(id) {
      this.$confirm(`确定删除此账号?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/tbuser/delete"),
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
    }
  }
};
</script>

<style lang="scss" scoped>
.btn_color {
  color: red;
}
</style>
