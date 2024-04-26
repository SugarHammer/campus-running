<template>
  <div class="mod-role">
    <el-form
      :inline="true"
      :model="dataForm"
      @keyup.enter.native="getDataList()"
    >
      <el-form-item>
        <el-select
          v-model="dataForm.messageType"
          placeholder="消息状态"
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
        prop="message"
        header-align="center"
        align="center"
        label="消息内容"
        :show-overflow-tooltip="true"
      >
      </el-table-column>
      <el-table-column
        prop="username"
        header-align="center"
        align="center"
        label="用户名"
        :show-overflow-tooltip="true"
      >
      </el-table-column>
      <el-table-column header-align="center" align="center" label="消息类型">
        <template slot-scope="scope">
          <div class="table-contents">
            <el-tag :type="scope.row.messageType ? 'success' : 'danger'">
              {{ scope.row.messageType ? "留言" : "举报" }}
            </el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="messageTime"
        header-align="center"
        align="center"
        width="180"
        label="创建时间"
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
        messageType: ""
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      options: [
        {
          value: "1",
          label: "留言"
        },
        {
          value: "0",
          label: "举报"
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
        url: this.$http.adornUrl("/basmessage/list"),
        method: "get",
        params: this.$http.adornParams({
          page: this.pageIndex,
          limit: this.pageSize,
          messageType: this.dataForm.messageType
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
      this.$confirm(`确定删除此消息?`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl("/basmessage/delete"),
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

<style lang="scss" scoped></style>
