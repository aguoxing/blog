<template>
  <div>
    <Card :dis-hover="true">
      <p slot="title">目录</p>
      <div class="toc-container" />
      <Anchor show-ink :offset-top="70" container="toc-container">
        <AnchorLink href="" title="目录" />
        <AnchorLink href="" title="未完善" />
        <AnchorLink href="" title="未完善">
          <AnchorLink href="" title="未完善..." />
          <AnchorLink href="" title="未完善..." />
          <AnchorLink href="" title="未完善..." />
        </AnchorLink>
      </Anchor>
    </Card>
  </div>
</template>

<script>
export default {
}
</script>

<style>
.toc-container{
  display: flex;
  flex-direction: row;
  margin-bottom: 2px;
}
</style>
