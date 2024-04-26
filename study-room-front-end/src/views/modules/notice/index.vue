<template>
  <div class="mod-role">
    <el-form
      :inline="true"
      :model="dataForm"
      @keyup.enter.native="getDataList()"
    >
      <el-form-item>
        <el-input
          v-model="dataForm.noticeTitle"
          placeholder="公告标题"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="dataForm.noticeState"
          placeholder="发布状态"
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
        <el-button type="primary" @click="addOrUpdateHandle()">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;"
    >
      <el-table-column
        prop="noticeTitle"
        header-align="center"
        align="center"
        label="公告标题"
      >
      </el-table-column>
      <el-table-column
        prop="noticeContent"
        header-align="center"
        align="center"
        label="公告内容"
        :show-overflow-tooltip="true"
      >
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
        prop="updateTime"
        header-align="center"
        align="center"
        width="180"
        label="更改时间"
      >
      </el-table-column>
      <el-table-column header-align="center" align="center" label="发布状态">
        <template slot-scope="scope">
          <div class="table-contents">
            <el-switch
              :active-value="0"
              :inactive-value="1"
              v-model="scope.row.noticeState"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="switchChange(scope.row)"
            >
            </el-switch>
          </div>
        </template>
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
            @click="addOrUpdateHandle(scope.row)"
            >修改</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="deleteHandle(scope.row.noticeId)"
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
    <add-or-update
      v-if="addOrUpdateVisible"
      ref="addOrUpdate"
      @refreshDataList="getDataList"
      @handCancel="handCancel"
    ></add-or-update>
  </div>
</template>

<script>
import AddOrUpdate from "./notice-add-or-update";
export default {
  components: {
    AddOrUpdate
  },
  data() {
    return {
      dataForm: {
        noticeState: "",
        noticeTitle: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      addOrUpdateVisible: false,
      options: [
        {
          value: "",
          label: "全部"
        },
        {
          value: "0",
          label: "开启"
        },
        {
          value: "1",
          label: "关闭"
        },
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
        url: this.$http.adornUrl("/notice/list"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          noticeTitle: this.dataForm.noticeTitle,
          noticeState: this.dataForm.noticeState
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.data.list;
          this.totalPage = data.data.totalCount;
        } else {
            this.dataList = []
            this.totalPage = 0
        }
        this.dataListLoading = false;
      });
    },
    // 添加修改
    addOrUpdateHandle(row) {
      this.addOrUpdateVisible = true;
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(row);
      });
    },

    // 删除
    deleteHandle(id) {
      this.$confirm(`确定删除此公告?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/notice/delete"),
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
    handCancel() {
      this.addOrUpdateVisible = false;
    },
    switchChange(info) {
      console.log(info.noticeState);
      if (!info.noticeState) {
        console.log("开");
        this.$http({
          url: this.$http.adornUrl("/notice/updateStateOn?id=" + info.noticeId),
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
      } else {
        console.log("关");
        this.$http({
          url: this.$http.adornUrl(
            "/notice/updateStateOff?id=" + info.noticeId
          ),
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
      }
    }
  }
};
</script>

<style lang="scss" scoped></style>
